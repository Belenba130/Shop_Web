package com.example.shop_web.service.imp;

import com.example.shop_web.exception.BaseException;
import com.example.shop_web.model.dto.request.ShoppingCartRequest;
import com.example.shop_web.model.dto.response.ShoppingCartResponse;
import com.example.shop_web.model.entity.ProductsEntity;
import com.example.shop_web.model.entity.ShoppingCartEntity;
import com.example.shop_web.model.entity.UsersEntity;
import com.example.shop_web.repository.ProductRepository;
import com.example.shop_web.repository.ShoppingCartRepository;
import com.example.shop_web.repository.UserRepository;
import com.example.shop_web.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingServiceImp implements ShoppingService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public UsersEntity userUsing() {

        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        UsersEntity user = userRepository.findUsersEntityByUsername(name);
        shoppingCart.setUsersByUserId(user);
        shoppingCartRepository.save(shoppingCart);
        return user;
    }


    @Override
    public List<ShoppingCartResponse> showAll() {
        List<ShoppingCartEntity> shoppingCarts = shoppingCartRepository.findByUsersByUserId(userUsing());

        List<ShoppingCartResponse> responses = shoppingCarts.stream()
                .map(shoppingCartEntity -> convertToResponse(shoppingCartEntity, shoppingCartEntity.getProductsByProductId()))
                .collect(Collectors.toList());
        return responses;

    }


    @Override
    public ShoppingCartResponse addToCart(ShoppingCartRequest shoppingCartRequest) {
        Optional<ProductsEntity> productOptional = productRepository.findById(shoppingCartRequest.getProductId());
        ProductsEntity product = productOptional.orElseThrow(() -> new BaseException("RA-SP1-404"));
        if (product.getStockQuantity() < shoppingCartRequest.getOrderQuantity()) {
            throw new BaseException("RA-SP2-400");
        }
        int Stock = product.getStockQuantity() - shoppingCartRequest.getOrderQuantity();
        product.setStockQuantity(Stock);
        productRepository.save(product);
       ShoppingCartEntity existingItem = shoppingCartRepository.findByUsersByUserIdAndProductsByProductId(userUsing(), product);
        if (existingItem != null) {
            existingItem.setOrderQuantity(existingItem.getOrderQuantity() + shoppingCartRequest.getOrderQuantity());
            shoppingCartRepository.save(existingItem);
            return convertToResponse(existingItem, product);
        } else {
            ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
            shoppingCart.setUsersByUserId(userUsing());
            shoppingCart.setProductsByProductId(product);
            shoppingCart.setOrderQuantity(shoppingCartRequest.getOrderQuantity());
            shoppingCartRepository.save(shoppingCart);
            return convertToResponse(shoppingCart, product);
        }
    }


    @Override
    public ShoppingCartResponse updateCartItemQuantity(String cartItemId, int quantity) {
        Optional<ShoppingCartEntity> cartItemOptional = shoppingCartRepository.findById(cartItemId);
        ShoppingCartEntity cartItem = cartItemOptional.orElseThrow(() -> new BaseException("RA-SP3-404"));
        if (quantity <= 0) {
            throw new BaseException("RA-SP4-400");
        }

        ProductsEntity product = cartItem.getProductsByProductId();
        int currentStock = product.getStockQuantity();
        int quantityDiff = quantity - cartItem.getOrderQuantity();
        if (currentStock < quantityDiff) {
            throw new BaseException("RA-SP5-400");
        }
        cartItem.setOrderQuantity(quantity);
        shoppingCartRepository.save(cartItem);
        int remainingStock = currentStock - quantityDiff;
        product.setStockQuantity(remainingStock);
        productRepository.save(product);
        return convertToResponse(cartItem, product);
    }


    @Override
    public void deleteCartItem(String cartItemId) {
        // Kiểm tra xem mục giỏ hàng có tồn tại không
        Optional<ShoppingCartEntity> cartItemOptional = shoppingCartRepository.findById(cartItemId);
        ShoppingCartEntity cartItem = cartItemOptional.orElseThrow(() -> new BaseException("RA-SP6-404"));

        // Lấy thông tin sản phẩm của mục giỏ hàng
        ProductsEntity product = cartItem.getProductsByProductId();

        // Khôi phục số lượng tồn kho của sản phẩm
        int currentStock = product.getStockQuantity();
        int cartItemQuantity = cartItem.getOrderQuantity();
        int restoredStock = currentStock + cartItemQuantity;
        product.setStockQuantity(restoredStock);
        productRepository.save(product);

        // Xóa mục giỏ hàng
        shoppingCartRepository.delete(cartItem);
    }

    @Override
    public void deleteAllCartItem() {
        // Lấy tất cả các mục trong giỏ hàng của người dùng
        List<ShoppingCartEntity> cartItems = shoppingCartRepository.findByUsersByUserId(userUsing());

        // Khôi phục số lượng tồn kho của từng sản phẩm và xóa các mục giỏ hàng
        for (ShoppingCartEntity cartItem : cartItems) {
            ProductsEntity product = cartItem.getProductsByProductId();
            int currentStock = product.getStockQuantity();
            int cartItemQuantity = cartItem.getOrderQuantity();
            int restoredStock = currentStock + cartItemQuantity;
            product.setStockQuantity(restoredStock);
            productRepository.save(product);
            shoppingCartRepository.delete(cartItem);
        }
    }


    @Override
    public BigDecimal Payment() {
        List<ShoppingCartEntity> shoppingCarts = shoppingCartRepository.findByUsersByUserId(userUsing());
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCartEntity cartEntity : shoppingCarts) {
            BigDecimal productTotalPrice = cartEntity.getProductsByProductId().getUnitPrice()
                    .multiply(BigDecimal.valueOf(cartEntity.getOrderQuantity()));
            totalPrice = totalPrice.add(productTotalPrice);
        }
        return totalPrice;
    }

    @Override
    public List<ShoppingCartEntity> getAllListCart() {
        List<ShoppingCartEntity> shoppingCarts = shoppingCartRepository.findByUsersByUserId(userUsing());
        return shoppingCarts;
    }

    private ShoppingCartResponse convertToResponse(ShoppingCartEntity shoppingCartEntity, ProductsEntity productsEntity) {
        ShoppingCartResponse response = new ShoppingCartResponse();
        response.setShoppingCartId(shoppingCartEntity.getShoppingCartId());
        response.setProductName(productsEntity.getProductName());
        response.setUnitPrice(productsEntity.getUnitPrice());
        response.setOrderQuantity(shoppingCartEntity.getOrderQuantity());
        return response;
    }
}

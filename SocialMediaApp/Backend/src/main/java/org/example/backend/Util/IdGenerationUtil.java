package org.example.backend.Util;

import lombok.RequiredArgsConstructor;
import org.example.backend.models.Like;
import org.example.backend.models.Post;
import org.example.backend.models.User;
import org.example.backend.repository.LikeRepository;
import org.example.backend.repository.PostRepository;
import org.example.backend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class IdGenerationUtil {

    final UserRepository userRepository;
    final PostRepository postRepository;
    private final LikeRepository likeRepository;

    public String userCodeGenerator(){
        Optional<User> lastRow = userRepository.findTopByOrderByIdDesc();
        return lastRow.map(user -> {
            String lastRowCode = user.getUserCode();
            int numericPart = Integer.parseInt(lastRowCode.substring(1)) + 1;
            return "U" + String.format("%04d", numericPart);
        }).orElse("U0001");
    }

    public Integer postCodeGenerator(){
        Optional<Post> lastRow = postRepository.findMaxById();
        return lastRow.map(post -> {
            long lastRowCode = post.getId();
            int numericPart = Integer.parseInt(String.valueOf(lastRowCode)) + 1;
            return numericPart;
        }).orElse(1);
    }

    public Long LikeCodeGenerator(){
        Optional<Like> lastRow = likeRepository.findTopByOrderByIdDesc();
        return lastRow.map(like -> {
            Long lastRowCode = like.getId();
            Long numericPart = lastRowCode + 1;
            return numericPart;
        }).orElse(1L);
    }
//
//    public String productCodeGenerator(String categoryCode) {
//        Optional<Product> product = productRepository.findTopByOrderByProductIDDesc();
//        return product.map(pro -> {
//            String productCode = pro.getProductCode();
//            // Use regular expression to separate letters and numbers
//            String[] parts = productCode.split("(?<=\\D)(?=\\d)");
//            // Extract the letter and number parts
//            String numbers = parts[1];
//            // Convert the numeric part to an integer and add 1
//            int numericPart = Integer.parseInt(numbers) + 1;
//            return "L"+categoryCode.toUpperCase() + String.format("%04d", numericPart);
//        }).orElse("L"+categoryCode.toUpperCase()+"0001");
//    }

//    public String orderCoderGenerator(){
//        Optional<Orders> orders = orderRepository.findTopByOrderByOrderIDDesc();
//        return orders.map(ord ->{
//            String orderCode = ord.getOrderCode();
//            if (orderCode.contains("EX_")){
//                orderCode = orderCode.split("EX_")[1].trim();
//            }
//
//            // Use regular expression to separate letters and numbers
//            String[] parts = orderCode.split("(?<=\\D)(?=\\d)");
//            // Extract the letter and number parts
//            String numbers = parts[1];
//            // Convert the numeric part to an integer and add 1
//            int numericPart = Integer.parseInt(numbers) + 1;
//            return "L" + String.format("%07d", numericPart);
//        }).orElse("L"+"0000001");
//    }

//    //commison process code generator
//    public String commissionProcessCodeGenerator(){
//        Optional<CommissionProcess> commissionProcess = commissionProcessRepository.findTopByOrderByIdDesc();
//        return commissionProcess.map(pro -> {
//            String productCode = pro.getCommissionProcessId();
//            // Use regular expression to separate letters and numbers
//            String[] parts = productCode.split("(?<=\\D)(?=\\d)");
//            // Extract the letter and number parts
//            String numbers = parts[1];
//            // Convert the numeric part to an integer and add 1
//            int numericPart = Integer.parseInt(numbers) + 1;
//            return "COMM-PROC" + String.format("%04d", numericPart);
//        }).orElse("COMM-PROC"+"0001");
//    }

}


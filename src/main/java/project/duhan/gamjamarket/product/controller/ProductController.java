package project.duhan.gamjamarket.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.duhan.gamjamarket.common.auth.Login;
import project.duhan.gamjamarket.product.controller.dto.ProductRegisterRequest;
import project.duhan.gamjamarket.product.controller.dto.ProductUpdateRequest;
import project.duhan.gamjamarket.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> register(@Login Long memberId, @RequestBody ProductRegisterRequest request) {
        productService.register(request.toCommand(memberId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> update(@Login Long memberId, @PathVariable Long productId,
                                       @RequestBody ProductUpdateRequest request) {
        productService.update(request.toCommand(productId, memberId));
        return ResponseEntity.ok().build();
    }

}

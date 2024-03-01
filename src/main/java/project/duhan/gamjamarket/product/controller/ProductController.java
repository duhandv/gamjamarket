package project.duhan.gamjamarket.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.duhan.gamjamarket.common.DataResult;
import project.duhan.gamjamarket.common.auth.Login;
import project.duhan.gamjamarket.product.controller.dto.ProductRegisterRequest;
import project.duhan.gamjamarket.product.controller.dto.ProductUpdateRequest;
import project.duhan.gamjamarket.product.service.ProductQueryService;
import project.duhan.gamjamarket.product.service.ProductService;
import project.duhan.gamjamarket.product.service.dto.ProductQueryResponse;

import java.util.List;

@Tag(name = "Product")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductQueryService productQueryService;

    public ProductController(ProductService productService, ProductQueryService productQueryService) {
        this.productService = productService;
        this.productQueryService = productQueryService;
    }

    @Operation(summary = "상품 등록")
    @PostMapping
    public ResponseEntity<Void> register(@Login Long memberId, @RequestBody ProductRegisterRequest request) {
        productService.register(request.toCommand(memberId));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 수정")
    @PostMapping("/{productId}")
    public ResponseEntity<Void> update(@Login Long memberId, @PathVariable Long productId,
            @RequestBody ProductUpdateRequest request) {
        productService.update(request.toCommand(productId, memberId));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "내 동네 상품 조회")
    @GetMapping
    public ResponseEntity<DataResult<List<ProductQueryResponse>>> findAll(@Login Long memberId) {
        return ResponseEntity.ok(DataResult.of(productQueryService.findAll(memberId)));
    }

    @Operation(summary = "상품 좋아요")
    @PostMapping("/{productId}/like")
    public ResponseEntity<Void> like(@Login Long memberId, @PathVariable Long productId) {
        productService.like(memberId, productId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상품 좋아요 취소")
    @PostMapping("/{productId}/cancel-like")
    public ResponseEntity<Void> cancelLike(@Login Long memberId, @PathVariable Long productId) {
        productService.cancelLike(memberId, productId);
        return ResponseEntity.ok().build();
    }

}

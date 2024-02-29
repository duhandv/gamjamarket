package project.duhan.gamjamarket.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.duhan.gamjamarket.member.domain.Member;
import project.duhan.gamjamarket.member.domain.MemberRepository;
import project.duhan.gamjamarket.product.domain.Product;
import project.duhan.gamjamarket.product.service.dto.ProductQueryResponse;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductQueryService {

    private final ProductQueryDao productQueryDao;

    private final MemberRepository memberRepository;

    public ProductQueryService(ProductQueryDao productQueryDao, MemberRepository memberRepository) {
        this.productQueryDao = productQueryDao;
        this.memberRepository = memberRepository;
    }

    public List<ProductQueryResponse> findAll(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Product> products = productQueryDao.findAllByRegion(member.getRegion());
        return products.stream().map(ProductQueryResponse::new).toList();
    }

}

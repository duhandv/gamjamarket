package project.duhan.gamjamarket.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    void deleteByMemberIdAndProductId(Long memberId, Long productId);

}

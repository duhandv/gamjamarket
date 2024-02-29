package project.duhan.gamjamarket.product.domain;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LikeLifeCycleHandler {

    private final LikeRepository likeRepository;

    public LikeLifeCycleHandler(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @EventListener
    @Transactional
    public void handle(ProductLikedEvent event) {
        likeRepository.save(new Like(event.memberId(), event.productId()));
    }

    @EventListener
    @Transactional
    public void handle(ProductCancelLikedEvent event) {
        likeRepository.deleteByMemberIdAndProductId(event.memberId(), event.productId());
    }

}

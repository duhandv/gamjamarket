package project.duhan.gamjamarket.product.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class LikeLifeCycleHandlerTest {

    @InjectMocks
    private LikeLifeCycleHandler handler;

    @Mock
    private LikeRepository likeRepository;

    @Test
    void saveLike() {
        handler.handle(new ProductLikedEvent(1L, 1L));

        verify(likeRepository, times(1)).save(any());
    }

    @Test
    void deleteLike_whenCancelLike() {
        handler.handle(new ProductCancelLikedEvent(1L, 1L));

        verify(likeRepository, times(1)).deleteByMemberIdAndProductId(any(), any());
    }

}
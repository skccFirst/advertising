package youtube_RH;

import youtube_RH.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.security.Policy;
import java.util.StringTokenizer;

@Service
public class PolicyHandler{

    @Autowired
    AdvertisingSystemRepository avertisingSystemRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverEditedVideo_AdModified(@Payload EditedVideo editedVideo){
        if(editedVideo.isMe()){
            System.out.println("===================광고횟수 차감=====================");
            int viewCnt = editedVideo.getViewCount();
            AdvertisingSystem ads = new AdvertisingSystem();
            StringTokenizer st = new StringTokenizer(editedVideo.getAdList(), ",");
            int countTokens = st.countTokens();
            for (int i = 0; i < countTokens; i++) {
                String token = st.nextToken();
                long adId = Long.valueOf(token);
                avertisingSystemRepository.findById(adId).ifPresent(advertisingSystem -> advertisingSystem.minusAdCnt(editedVideo.getViewCount()));
            }

            System.out.println("##### listener editedVideo : " + editedVideo.toJson());
        }

    }
}

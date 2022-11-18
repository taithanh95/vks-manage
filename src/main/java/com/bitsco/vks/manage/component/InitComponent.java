package com.bitsco.vks.manage.component;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.manage.cache.CacheService;
import com.bitsco.vks.manage.entities.Decision;
import com.bitsco.vks.manage.entities.Law;
import com.bitsco.vks.manage.entities.Spp;
import com.bitsco.vks.manage.repository.DecisionRepository;
import com.bitsco.vks.manage.repository.LawRepository;
import com.bitsco.vks.manage.repository.SppRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Component
public class InitComponent {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.APPLICATION);
    @Autowired
    CacheService cacheService;

    @Autowired
    SppRepository sppRepository;

    @Autowired
    LawRepository lawRepository;

    @Autowired
    DecisionRepository decisionRepository;

    @PostConstruct
    public void initCache() {
        Date begin = new Date();
        try {
            LOGGER.info("Begin load spp redis-cache >>> " + begin);
            List<Spp> sppList = sppRepository.findAll();
            if (!ArrayListCommon.isNullOrEmpty(sppList))
                sppList.forEach(x -> cacheService.addSpp2RedisCache(x));
        } catch (Exception e) {
            LOGGER.error("Exception when load spp redis-cache", e);
        } finally {
            Date end = new Date();
            LOGGER.error("End load spp redis-cache >>> " + end + " duration = " + (end.getTime() - begin.getTime()));
        }
        try {
            begin = new Date();
            LOGGER.info("Begin load law redis-cache >>> " + begin);
            List<Law> lawList = lawRepository.findAll();
            if (!ArrayListCommon.isNullOrEmpty(lawList))
                lawList.forEach(x -> cacheService.addLawRedisCache(x));
        } catch (Exception e) {
            LOGGER.error("Exception when load law redis-cache", e);
        } finally {
            Date end = new Date();
            LOGGER.error("End load law redis-cache >>> " + end + " duration = " + (end.getTime() - begin.getTime()));
        }
        try {
            begin = new Date();
            LOGGER.info("Begin load decision redis-cache >>> " + begin);
            List<Decision> decisionList = decisionRepository.findAll();
            if (!ArrayListCommon.isNullOrEmpty(decisionList))
                decisionList.forEach(x -> cacheService.addDecision2RedisCache(x));
        } catch (Exception e) {
            LOGGER.error("Exception when load decision redis-cache", e);
        } finally {
            Date end = new Date();
            LOGGER.error("End load decision redis-cache >>> " + end + " duration = " + (end.getTime() - begin.getTime()));
        }
    }
}

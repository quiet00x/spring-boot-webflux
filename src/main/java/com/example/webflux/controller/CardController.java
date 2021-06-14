package com.example.webflux.controller;

import com.example.webflux.domain.CardEntity;
import com.example.webflux.service.ICardService;
import com.example.webflux.vo.CardVo;
import com.example.webflux.vo.ResponseVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2021/6/7 - 23:30
 * @author: Mr_Bangb
 */
@RestController
@RequestMapping("card/")
public class CardController {
    @Resource
    private ICardService cardService;

    @RequestMapping(value = "export/{id}", method = RequestMethod.GET)
    public ResponseVO<List<CardEntity>> export(@PathVariable("id") Long id) {
        CardVo cardVo = new CardVo();
        cardVo.setId(id);
        List<CardEntity> cardEntityList = cardService.exportMockito(cardVo);
        return ResponseVO.buildSuccess(Optional.ofNullable(cardEntityList)
                .orElse(Collections.emptyList()));
    }
}
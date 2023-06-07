package com.ling.project_backend.service;

import com.ling.project_backend.domain.Card;
import com.ling.project_backend.utils.Result;

public interface CardService {

    Result createCard(Card card);

    Result deleteCard(Card card);

    Result changeCard(Card card);

    Result searchCards(Card card);

    Result getCard(Integer id);

    Result getCardCount();

    Result getCards(int length);

}

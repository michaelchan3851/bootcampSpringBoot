package com.stock.stocktradeapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stock.stocktradeapp.data.Book;
import com.stock.stocktradeapp.dto.req.PlaceOrderDTO;
import com.stock.stocktradeapp.dto.resp.OrderBookDTO;
import com.stock.stocktradeapp.model.Order;
import com.stock.stocktradeapp.model.Stock;
import com.stock.stocktradeapp.model.User;
import com.stock.stocktradeapp.service.OrderBookService;

@Service
public class OrderBookServiceImpl implements OrderBookService {

  @Override
  public Stock orderBook(String symbol) {
    return Stock.getStock(symbol);
  }

  @Override
  public Order order(String userId, String symbol, PlaceOrderDTO tradeDTO) {
    // TBC. userId handling
    return new User(userId).placeOrder(tradeDTO);
  }
}

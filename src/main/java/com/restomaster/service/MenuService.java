package com.restomaster.service;

import com.restomaster.model.MenuItem;
import com.restomaster.repository.MenuItemRepository;
import com.restomaster.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuItemRepository menuItemRepository;
    private final OrderRepository orderRepository;

    public List<MenuItem> getAllItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    // AKILLI ÖNERİ SİSTEMİ:
    public List<MenuItem> getSmartSuggestions(Long itemId) {
        List<MenuItem> suggestions = orderRepository.findTopSuggestions(itemId);

        if (suggestions.isEmpty()) {
            return menuItemRepository.findById(itemId)
                    .map(item -> item.getSuggestedItems().stream().toList())
                    .orElse(List.of());
        }
        return suggestions;
    }
}
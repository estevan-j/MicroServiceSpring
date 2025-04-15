package com.example.InventoryService.controller;

import com.example.InventoryService.model.Inventory;
import com.example.InventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sky-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@PathVariable("sky-code") String skyCode) {
        return inventoryService.isInStock(skyCode);
    }
}

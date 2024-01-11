package com.galo.minhasfinancas.entrypoint.api.resource;

import com.galo.minhasfinancas.core.execution.ItemImpl;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.framework.api.resources.AbsResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.ITEM_URL;

@RestController
@RequestMapping(value = "/api" + ITEM_URL)
public class ItemApi extends AbsResource<Item, ItemImpl, ItemDTO> {

  public ItemApi(ItemImpl service) {
    super(service);
  }
}

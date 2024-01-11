package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.ItemRepository;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.mappers.ItemMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemImpl extends ExecutionAbs<Item, ItemRepository, ItemDTO, ItemMapper> {
  @Autowired
  protected ItemImpl(ItemRepository repo, ItemMapper mapper) {
    super(repo, mapper);
  }

  @Override
  public String getName() {
    return Item.class.getSimpleName();
  }

}

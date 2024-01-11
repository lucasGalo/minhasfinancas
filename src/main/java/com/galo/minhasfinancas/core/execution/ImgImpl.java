package com.galo.minhasfinancas.core.execution;

import com.galo.minhasfinancas.dataprovider.repositories.ImgRepository;
import com.galo.minhasfinancas.domain.dto.ImgDTO;
import com.galo.minhasfinancas.domain.entity.Img;
import com.galo.minhasfinancas.domain.mappers.ImgMapper;
import com.galo.minhasfinancas.framework.api.execution.ExecutionAbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgImpl extends ExecutionAbs<Img, ImgRepository, ImgDTO, ImgMapper> {
  @Autowired
  protected ImgImpl(ImgRepository repo, ImgMapper mapper) {
    super(repo, mapper);
  }
  @Override
  public String getName() {
    return Img.class.getSimpleName();
  }
}

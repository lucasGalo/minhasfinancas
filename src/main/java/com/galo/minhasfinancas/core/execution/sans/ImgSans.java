package com.galo.minhasfinancas.core.execution.sans;

import com.galo.minhasfinancas.dataprovider.repositories.ImgRepository;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Img;
import com.galo.minhasfinancas.domain.entity.Usuario;

import java.util.Objects;

import static com.galo.minhasfinancas.sources.FilesUtil.deletarFile;
import static com.galo.minhasfinancas.sources.FilesUtil.pathUsuarios;
import static com.galo.minhasfinancas.sources.FilesUtil.saveFile;

public class ImgSans {
  public static void imgSet(UsuarioDTO dto, Usuario usuario, ImgRepository imgRepository) {
    String imgNome = getImageNome(dto.getEmail());
    String url = pathUsuarios + imgNome;
    if (usuario.getImg() == null)
      usuario.setImg(new Img(null, true, imgNome, url));

    if (!Objects.isNull(usuario.getId()))
      deletarFile(pathUsuarios + imgNome);

    if (dto.getImgbytes() != null) {
      saveFile(url, dto.getImgbytes());
    } else {
      imgNome = "";
      url = "";
    }

    usuario.getImg().setUrl(url);
    usuario.getImg().setNome(imgNome);

    imgRepository.save(usuario.getImg());
  }

  static String getImageNome(String email) {
    return email + ".jpg";
  }

}
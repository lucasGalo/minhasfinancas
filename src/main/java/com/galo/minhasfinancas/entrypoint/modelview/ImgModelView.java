package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.ImgImpl;
import com.galo.minhasfinancas.domain.dto.ImgDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.IMG_URL;
import static com.galo.minhasfinancas.sources.FilesUtil.pathUsuarios;

@Controller
@RequestMapping({IMG_URL})
public class ImgModelView extends ControllerGeneric<ImgDTO> {
  public ImgModelView(final ImgImpl impl) {super(impl, new ImgDTO(), IMG_URL);}

  @RequestMapping(value = "download/{nome:.+}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<InputStreamResource> downloadImage(@PathVariable String nome) throws FileNotFoundException {
    File initialFile = new File(pathUsuarios);
    InputStream is = new FileInputStream(initialFile+nome);
    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("image/" + FilenameUtils.getExtension(nome)))
            .header("Content-disposition", "attachment; filename=" + nome)
            .body(new InputStreamResource(is));
  }
}

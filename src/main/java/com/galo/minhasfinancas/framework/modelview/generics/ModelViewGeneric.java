package com.galo.minhasfinancas.framework.modelview.generics;

import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.enums.ColorEnum;
import com.galo.minhasfinancas.sources.StringUtil;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Lucas Galo ( galo_pc@outlook.com )
 */
public abstract class ModelViewGeneric<T extends DTO>{

//  protected final String SERVER_SERVLET_CONTEXTPATH = "/prosat";
  protected final String SERVER_SERVLET_CONTEXTPATH = "";

  protected String PAGE_LIST;
  protected String PAGE_FORM;
  protected String NEW_URL;
  protected String PHOTO_URL;
  protected String LIST_URL;
  protected String EDIT_URL;
  protected String TITLE_LIST;
  protected String TITLE_NEW;
  protected String TITLE_EDIT;
  protected String SINGLE_OBJECT = "single";
  protected String FORM_OBJECT_NAME;
  protected String LIST_OBJECT = "list";
  protected String EDITABLE_MESSAGE;
  protected String SUCCESS_MESSAGE;
  protected String REMOVE_MESSAGE= "Erro ao excluir item , erro: ";

  public ModelViewGeneric(final String url_base) {

    final String single = url_base.substring(1, url_base.length() - 1);
    this.TITLE_LIST = "Lista de " + single;
    this.TITLE_NEW = "Novo " + single;
    this.TITLE_EDIT = "Editar " + single;

    final String capitalize = StringUtil.capitalize(single);
    this.FORM_OBJECT_NAME = capitalize;
    this.SUCCESS_MESSAGE = capitalize +" cadastrado(a) com sucesso!";
    this.EDITABLE_MESSAGE = capitalize +" alterado(a) com sucesso!";

    this.NEW_URL = SERVER_SERVLET_CONTEXTPATH + url_base + "/novo";
    this.PHOTO_URL = SERVER_SERVLET_CONTEXTPATH + url_base + "/foto";
    this.LIST_URL = SERVER_SERVLET_CONTEXTPATH + url_base + "/list";
    this.EDIT_URL = SERVER_SERVLET_CONTEXTPATH + url_base + "/editar";

    this.PAGE_LIST = "paginas" + url_base + "/list_" + url_base.replace("/", "");
    this.PAGE_FORM = "paginas" + url_base + "/form_" + url_base.replace("/", "");
  }

  protected ModelAndView newFormPageCustom(final Map<String, List> hm, final T dto, final List erros) {
    ModelAndView modelAndView = getModelAndView(hm, dto, null);
    modelAndView.addObject("erros", erros);
    modelAndView.addObject("FRM", TITLE_NEW);
    return modelAndView;
  }
  protected ModelAndView editFormPage(final Map<String, List> hm, final T dto, final List erros) {
    ModelAndView modelAndView = getModelAndView(hm, dto, null);
    modelAndView.addObject("erros", erros);
    modelAndView.addObject("FRM", TITLE_EDIT);
    return modelAndView;
  }
  private ModelAndView getModelAndView(final Map<String, List> hm,final T dto, String url) {
    ModelAndView modelAndView = new ModelAndView(url == null ? PAGE_FORM : url);
    modelAndView.addObject("NEW_URL", NEW_URL);
    modelAndView.addObject("PHOTO_URL", PHOTO_URL);
    modelAndView.addObject("LIST_URL", LIST_URL);
    modelAndView.addObject("EDIT_URL", EDIT_URL);
    modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
    if(null != hm)
      for (Map.Entry<String, List> map : hm.entrySet()) {
        modelAndView.addObject(map.getKey(), map.getValue());
      }
    modelAndView.addObject(SINGLE_OBJECT, dto);
    return modelAndView;
  }
  protected ModelAndView listPage(final List list, final String message,final List erros, ColorEnum colorEnum) {
    ModelAndView modelAndView = getModelAndViewList(list);
    modelAndView.addObject("message", message);
    modelAndView.addObject("alertClass", "alert-"+colorEnum.getName());
    modelAndView.addObject("erros", erros);
    return modelAndView;
  }
  private ModelAndView getModelAndViewList(final List list) {
    ModelAndView modelAndView = new ModelAndView(PAGE_LIST);
    modelAndView.addObject("NEW_URL", NEW_URL);
    modelAndView.addObject("PHOTO_URL", PHOTO_URL);
    modelAndView.addObject("LIST_URL", LIST_URL);
    modelAndView.addObject("EDIT_URL", EDIT_URL);
    modelAndView.addObject("FRM", TITLE_LIST);
    modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
    modelAndView.addObject(LIST_OBJECT, list);
    return modelAndView;
  }
  protected ModelAndView newFormPageOfObject(final Object object) {
    String urlPage = PAGE_FORM;
    if (object instanceof LinkedHashMap) {
      String page = ((LinkedHashMap<?, String>) object).get("urlPage");
      urlPage = page.isEmpty() ? urlPage : page;
    }
    ModelAndView modelAndView = new ModelAndView(urlPage);
    modelAndView.addObject("EDIT_URL", EDIT_URL);
    modelAndView.addObject("NEW_URL", NEW_URL);
    modelAndView.addObject("PHOTO_URL", PHOTO_URL);
    modelAndView.addObject("FRM", TITLE_NEW);
    modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
    modelAndView.addObject(SINGLE_OBJECT, object);
    return modelAndView;
  }
  protected ModelAndView newFormPageOfObject(final Object object,final  String urlPage) {
    ModelAndView modelAndView = new ModelAndView(urlPage);
    modelAndView.addObject("EDIT_URL", EDIT_URL);
    modelAndView.addObject("NEW_URL", NEW_URL);
    modelAndView.addObject("PHOTO_URL", PHOTO_URL);
    modelAndView.addObject("FRM", TITLE_NEW);
    modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
    modelAndView.addObject(SINGLE_OBJECT, object);
    return modelAndView;
  }

  protected ModelAndView newFormPageCustom(final Map<String, List> hm, final T dto, final List erros, String url) {
    ModelAndView modelAndView = getModelAndView(hm, dto, url);
    modelAndView.addObject("erros", erros);
    modelAndView.addObject("FRM", TITLE_NEW);
    return modelAndView;
  }

  protected ModelAndView listPageCustom(final Map<String, List> hm, final List list, final String message,final List erros, ColorEnum colorEnum, String urlPage) {
    ModelAndView modelAndView = new ModelAndView(urlPage == null ? PAGE_LIST : urlPage);
    modelAndView.addObject("NEW_URL", NEW_URL);
    modelAndView.addObject("PHOTO_URL", PHOTO_URL);
    modelAndView.addObject("LIST_URL", LIST_URL);
    modelAndView.addObject("EDIT_URL", EDIT_URL);
    modelAndView.addObject("FRM", TITLE_LIST);
    modelAndView.addObject(LIST_OBJECT, list);
    modelAndView.addObject("message", message);
    modelAndView.addObject("alertClass", "alert-"+colorEnum.getName());
    modelAndView.addObject("erros", erros);
    if(null != hm)
      for (Map.Entry<String, List> map : hm.entrySet()) {
        modelAndView.addObject(map.getKey(), map.getValue());
      }
    return modelAndView;
  }
}

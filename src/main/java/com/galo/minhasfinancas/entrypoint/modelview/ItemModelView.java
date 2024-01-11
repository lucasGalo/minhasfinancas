package com.galo.minhasfinancas.entrypoint.modelview;

import com.galo.minhasfinancas.core.execution.ItemImpl;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.framework.modelview.generics.ControllerGeneric;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.ITEM_URL;

@Controller
@RequestMapping({ITEM_URL})
public class ItemModelView extends ControllerGeneric<ItemDTO> {
    public ItemModelView(final ItemImpl impl) {
        super(impl, new ItemDTO(), ITEM_URL);
    }
}

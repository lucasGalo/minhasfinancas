package com.galo.minhasfinancas.entrypoint.api.resource;

import com.galo.logstach.interfaces.LogProcessInterface;
import com.galo.minhasfinancas.core.execution.TelemetriaImpl;
import com.galo.minhasfinancas.domain.dto.TelemetriaDTO;
import com.galo.minhasfinancas.domain.entity.Telemetria;
import com.galo.minhasfinancas.framework.api.resources.AbsResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.entrypoint.NamesResouces.TELEMETRIA_URL;
import static com.galo.minhasfinancas.sources.StringUtil.getUUID;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/api"+TELEMETRIA_URL)
public class TelemetriaApi extends AbsResource<Telemetria, TelemetriaImpl, TelemetriaDTO> {
    public TelemetriaApi(TelemetriaImpl impl) {
        super(impl);
    }

    @RequestMapping(value = "/ano/{ano}", method = GET)
    public List<TelemetriaDTO> findAllAno(@PathVariable String ano) {
        try (LogProcessInterface log = logs.get("[KEY] "+getUUID())) {
            log.info(request.format("[GET] [findAllAno] - [REQUEST]  @PathVariable : %s", ano));
            List<TelemetriaDTO> list =  service.findAllAno(ano);
            list.stream().peek(it -> it.setClientinfo(null)).collect(Collectors.toList());
            log.info(request.format("[GET] [findAllAno] - [RESPONSE] @OBJ: : %s", list));
            return list;
        }
    }
}

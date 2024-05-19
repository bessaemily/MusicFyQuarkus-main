package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.TipoConsulta;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoConsultaConverter implements AttributeConverter<TipoConsulta, Integer>{

    @Override
    public Integer convertToDatabaseColumn(TipoConsulta tipoConsulta) {
       return tipoConsulta.getId();
    }

    @Override
    public TipoConsulta convertToEntityAttribute(Integer id) {
        return TipoConsulta.valueOf(id);
    }

}
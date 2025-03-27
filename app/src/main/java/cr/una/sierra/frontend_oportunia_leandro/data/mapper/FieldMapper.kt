package cr.una.sierra.frontend_oportunia_leandro.data.mapper

import cr.una.sierra.frontend_oportunia_leandro.data.datasource.model.FieldDto
import cr.una.sierra.frontend_oportunia_leandro.domain.model.Field

class FieldMapper {

    fun mapToDomain(dto: FieldDto): Field = Field(
        id = dto.id,
        name = dto.name
    )

    fun mapToDto(field: Field): FieldDto = FieldDto(
        id = field.id,
        name = field.name
    )
}
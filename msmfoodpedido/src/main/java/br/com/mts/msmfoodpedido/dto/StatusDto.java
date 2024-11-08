package br.com.mts.msmfoodpedido.dto;

import br.com.mts.msmfoodpedido.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private Status status;
}

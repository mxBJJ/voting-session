package com.maxmendes.votacaoapi.impl.integration.response;

import com.maxmendes.votacaoapi.impl.enums.CpfAvailable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CpfValidatorResponse {

    private CpfAvailable status;

}

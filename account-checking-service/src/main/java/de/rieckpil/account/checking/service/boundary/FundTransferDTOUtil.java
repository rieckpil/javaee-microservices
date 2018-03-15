/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.account.checking.service.boundary;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author Philip
 */
public class FundTransferDTOUtil {

    public static String fundTransferDTOToJson(FundTransferDTO fundTransferDTO) {
        Jsonb jsonB = JsonbBuilder.create();
        return jsonB.toJson(fundTransferDTO);
    }

    public static FundTransferDTO jsonToFundTransferDTO(String fundTransferDTOJson) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(fundTransferDTOJson, FundTransferDTO.class);
    }
}

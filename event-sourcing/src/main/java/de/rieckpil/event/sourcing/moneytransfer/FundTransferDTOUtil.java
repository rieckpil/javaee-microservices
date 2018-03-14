/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rieckpil.event.sourcing.moneytransfer;

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

}

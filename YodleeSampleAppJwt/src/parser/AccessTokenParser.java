/*
* Copyright (c) 2017 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/

package parser;

import com.google.gson.Gson;
import beans.AccessToken;


import java.io.IOException;

/**
 * Created by DRamachandra on 9/20/2017.
 */
public class AccessTokenParser implements Parser {

    private String fqcn = this.getClass().getName();
    public AccessToken parseJSON(String json) throws IOException
    {
       
        Gson gson = new Gson();
        return (AccessToken)gson.fromJson(json, AccessToken.class);
    }
}

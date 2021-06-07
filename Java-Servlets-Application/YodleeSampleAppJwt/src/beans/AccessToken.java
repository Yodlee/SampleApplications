/*
* Copyright (c) 2017 Yodlee, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of Yodlee, Inc.
* Use is subject to license terms.
*/

package beans;


public class AccessToken {

         User user;
         
         /**
          * 
          * @return
          */

        public User getUser ()
        {
            return user;
        }
        
        /**
         * 
         * @param user
         */
         
        public void setUser (User user)
        {
            this.user = user;
        }


}

package com.example.webflux.demo.enums;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.webflux.demo.constants.Constants;
import com.example.webflux.demo.functions.ISelectListFunction;
import com.example.webflux.demo.functions.ISelectObjFunction;
import com.example.webflux.domain.UserBean;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/19 - 22:13
 * @author: Mr_Bangb
 */
@AllArgsConstructor
public enum SelectEnum {

    KEY_01(Constants.KEY_01, Constants.VALUE_01){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    },
    KEY_02(Constants.KEY_01, Constants.VALUE_02){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    },

    KEY_03(Constants.KEY_01, Constants.VALUE_03){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    },
    KEY_04(Constants.KEY_01, Constants.VALUE_04){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    },
    KEY_05(Constants.KEY_01, Constants.VALUE_05){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    },
    KEY_06(Constants.KEY_01, Constants.VALUE_06){
        @Override
        public ISelectObjFunction selectUser() {
            return null;
        }

        @Override
        public ISelectListFunction selectUserList() {
            return null;
        }
    };

    private String key;

    private String value;

    private String getKey(){
        return this.key;
    }

    private String getValue(){
        return this.value;
    }

    public abstract ISelectObjFunction selectUser();

    public abstract ISelectListFunction selectUserList();

}

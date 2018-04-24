package com.mall.zhangxuan.dto;



public class ResponseModel<T> {

    private Integer code;
    private String msg;
    private Long count;
    private T data;

    public static class ResponseModelBuilder<T>{
        private Integer code;
        private String msg;
        private Long count;
        private T data;

        public ResponseModelBuilder code(Integer code){
            this.code = code;
            return this;
        }

        public ResponseModelBuilder message(String message){
            this.msg = message;
            return this;
        }

        public ResponseModelBuilder count(Long count){
            this.count = count;
            return this;
        }

        public ResponseModelBuilder data(T data){
            this.data = data;
            return this;
        }

        public ResponseModel build(){
            return new ResponseModel(this);
        }


    }


    public ResponseModel(){};

    public ResponseModel(ResponseModelBuilder<T> builder){
        this.code = builder.code;
        this.msg = builder.msg;
        this.count = builder.count;
        this.data = builder.data;

    }
    public static ResponseModelBuilder builder(){
        return new ResponseModelBuilder();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Long getCount() {
        return count;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString(){

        return "ReponseModel{ \n"+
                "code: "+code+",\n"
                +"msg: "+ msg +",\n"
                +"data: "+data+"\n"
                +"}";

    }




}

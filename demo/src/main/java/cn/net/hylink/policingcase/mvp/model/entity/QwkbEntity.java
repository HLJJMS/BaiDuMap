package cn.net.hylink.policingcase.mvp.model.entity;

import java.util.List;

public class QwkbEntity {

    /**
     * reason : null
     * result : {"result":{"list":[{"schedule_begin_time":"2020-03-25 14:00:00","label_area_name":"","idCard":"23062219800101005X","sign_status":"qd","schedule_end_time":"2020-03-25 22:00:00","vehicle_license_plate":"粤B88888","stauts":2,"schedule_date":"2020-03-25","police_message":[{"idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","id":"47","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-26 09:00:00","label_area_name":"","idCard":"23062219800101005X","schedule_end_time":"2020-03-26 16:00:00","stauts":1,"schedule_date":"2020-03-26","police_message":[{"idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","id":"47","type":"N","department":"230000000000"},{"idCard":"230125199905089572","contact":"15046050850","name":"蛤蛤","pcard":"5089572","id":"636","type":"N","department":"230000000000"},{"idCard":"231005197709167777","contact":"13632001202","name":"新测试","pcard":"2121","id":"649","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-29 03:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-29 09:00:00","stauts":3,"schedule_date":"2020-03-29","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"},{"departmentName":"黑龙江省","idCard":"236521199812252120","contact":"13522221122","name":"装备监管2","pcard":"33444007","photo":"","id":"2398","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-30 03:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-30 09:00:00","stauts":3,"schedule_date":"2020-03-30","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"},{"departmentName":"黑龙江省","idCard":"236521199812252120","contact":"13522221122","name":"装备监管2","pcard":"33444007","photo":"","id":"2398","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-31 09:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-31 17:00:00","stauts":3,"schedule_date":"2020-03-31","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"}]}]}}
     */


    /**
     * result : {"list":[{"schedule_begin_time":"2020-03-25 14:00:00","label_area_name":"","idCard":"23062219800101005X","sign_status":"qd","schedule_end_time":"2020-03-25 22:00:00","vehicle_license_plate":"粤B88888","stauts":2,"schedule_date":"2020-03-25","police_message":[{"idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","id":"47","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-26 09:00:00","label_area_name":"","idCard":"23062219800101005X","schedule_end_time":"2020-03-26 16:00:00","stauts":1,"schedule_date":"2020-03-26","police_message":[{"idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","id":"47","type":"N","department":"230000000000"},{"idCard":"230125199905089572","contact":"15046050850","name":"蛤蛤","pcard":"5089572","id":"636","type":"N","department":"230000000000"},{"idCard":"231005197709167777","contact":"13632001202","name":"新测试","pcard":"2121","id":"649","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-29 03:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-29 09:00:00","stauts":3,"schedule_date":"2020-03-29","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"},{"departmentName":"黑龙江省","idCard":"236521199812252120","contact":"13522221122","name":"装备监管2","pcard":"33444007","photo":"","id":"2398","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-30 03:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-30 09:00:00","stauts":3,"schedule_date":"2020-03-30","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"},{"departmentName":"黑龙江省","idCard":"236521199812252120","contact":"13522221122","name":"装备监管2","pcard":"33444007","photo":"","id":"2398","type":"N","department":"230000000000"}]},{"schedule_begin_time":"2020-03-31 09:00:00","label_area_name":"网格1","idCard":"23062219800101005X","schedule_end_time":"2020-03-31 17:00:00","stauts":3,"schedule_date":"2020-03-31","police_message":[{"departmentName":"黑龙江省","idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","photo":"","id":"47","type":"N","department":"230000000000"}]}]}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * schedule_begin_time : 2020-03-25 14:00:00
             * label_area_name :
             * idCard : 23062219800101005X
             * sign_status : qd
             * schedule_end_time : 2020-03-25 22:00:00
             * vehicle_license_plate : 粤B88888
             * stauts : 2
             * schedule_date : 2020-03-25
             * police_message : [{"idCard":"23062219800101005X","contact":"13154516666","name":"车载测试","pcard":"0","id":"47","type":"N","department":"230000000000"}]
             */

            private String schedule_begin_time;
            private String label_area_name;
            private String idCard;
            private String sign_status;
            private String schedule_end_time;
            private String vehicle_license_plate;
            private int stauts;
            private String schedule_date;
            private List<PoliceMessageBean> police_message;

            public String getSchedule_begin_time() {
                return schedule_begin_time;
            }

            public void setSchedule_begin_time(String schedule_begin_time) {
                this.schedule_begin_time = schedule_begin_time;
            }

            public String getLabel_area_name() {
                return label_area_name;
            }

            public void setLabel_area_name(String label_area_name) {
                this.label_area_name = label_area_name;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public String getSign_status() {
                return sign_status;
            }

            public void setSign_status(String sign_status) {
                this.sign_status = sign_status;
            }

            public String getSchedule_end_time() {
                return schedule_end_time;
            }

            public void setSchedule_end_time(String schedule_end_time) {
                this.schedule_end_time = schedule_end_time;
            }

            public String getVehicle_license_plate() {
                return vehicle_license_plate;
            }

            public void setVehicle_license_plate(String vehicle_license_plate) {
                this.vehicle_license_plate = vehicle_license_plate;
            }

            public int getStauts() {
                return stauts;
            }

            public void setStauts(int stauts) {
                this.stauts = stauts;
            }

            public String getSchedule_date() {
                return schedule_date;
            }

            public void setSchedule_date(String schedule_date) {
                this.schedule_date = schedule_date;
            }

            public List<PoliceMessageBean> getPolice_message() {
                return police_message;
            }

            public void setPolice_message(List<PoliceMessageBean> police_message) {
                this.police_message = police_message;
            }

            public static class PoliceMessageBean {
                /**
                 * idCard : 23062219800101005X
                 * contact : 13154516666
                 * name : 车载测试
                 * pcard : 0
                 * id : 47
                 * type : N
                 * department : 230000000000
                 */

                private String idCard;
                private String contact;
                private String name;
                private String pcard;
                private String id;
                private String type;
                private String department;

                public String getIdCard() {
                    return idCard;
                }

                public void setIdCard(String idCard) {
                    this.idCard = idCard;
                }

                public String getContact() {
                    return contact;
                }

                public void setContact(String contact) {
                    this.contact = contact;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPcard() {
                    return pcard;
                }

                public void setPcard(String pcard) {
                    this.pcard = pcard;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getDepartment() {
                    return department;
                }

                public void setDepartment(String department) {
                    this.department = department;
                }
            }
        }
    }

}

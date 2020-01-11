package com.nextlink.studentmanager;


    public class attmodel {
        private int id;
        private String subjectName;
        private int tAttendance;
        private int pAttendance;
       // private String status;
//        private double price;
//        private int image;

        public attmodel() {}

        public attmodel(int id, String subjectName, int pAttendance, int tAttendance) {
            this.id = id;
            this.subjectName = subjectName;
            this.tAttendance = tAttendance;
            this.pAttendance= pAttendance;
            //this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public int gettAttendance() {
            return tAttendance;
        }

        public int getpAttendance() {
            return pAttendance;
        }


        public void setId(int id) {
            this.id = id;
        }

        public void setSubjectName(String subjectName){this.subjectName = subjectName;}

        public void settAttendance(int tAttendance) {
            this.tAttendance = tAttendance;
        }

        public void setpAttendance(int pAttendance) {
            this.pAttendance = pAttendance;
        }


    }


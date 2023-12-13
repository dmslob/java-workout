package com.dmslob.state;

public enum LeaveRequestState {
    SUBMITTED {
        @Override
        public LeaveRequestState next() {
            return ESCALATED;
        }

        @Override
        public String responsible() {
            return "Employee";
        }
    },
    ESCALATED {
        @Override
        public LeaveRequestState next() {
            return APPROVED;
        }

        @Override
        public String responsible() {
            return "Team Leader";
        }
    },
    APPROVED {
        @Override
        public LeaveRequestState next() {
            return this;
        }

        @Override
        public String responsible() {
            return "Department Manager";
        }
    };

    public abstract LeaveRequestState next();

    public abstract String responsible();
}

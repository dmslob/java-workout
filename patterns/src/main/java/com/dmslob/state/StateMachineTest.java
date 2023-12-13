package com.dmslob.state;

public class StateMachineTest {

    public static void main(String[] args) {
        LeaveRequestState state = LeaveRequestState.SUBMITTED;

        state = state.next();
        assert LeaveRequestState.ESCALATED == state;

        state = state.next();
        assert LeaveRequestState.APPROVED == state;

        state = state.next();
        assert LeaveRequestState.APPROVED == state;
    }
}

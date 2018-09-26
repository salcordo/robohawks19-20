package robohawks.async.io;

import android.util.Log;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by fchoi on 12/1/2015.
 */
public class EventGamepad {

    boolean dpad_up_previous_1  = false;
    boolean dpad_down_previous_1  = false;
    boolean dpad_left_previous_1  = false;
    boolean dpad_right_previous_1  = false;
    boolean a_previous_1  = false;
    boolean b_previous_1  = false;
    boolean x_previous_1  = false;
    boolean y_previous_1  = false;
    boolean guide_previous_1  = false;
    boolean start_previous_1  = false;
    boolean back_previous_1  = false;
    boolean left_bumper_previous_1  = false;
    boolean right_bumper_previous_1  = false;
    boolean left_stick_button_previous_1  = false;
    boolean right_stick_button_previous_1  = false;

    float left_stick_x_previous_1  = 0.0F;
    float left_stick_y_previous_1  = 0.0F;
    float right_stick_x_previous_1  = 0.0F;
    float right_stick_y_previous_1  = 0.0F;

    float left_trigger_previous_1  = 0.0F;
    float right_trigger_previous_1  = 0.0F;

    boolean dpad_up_previous_2 = false;
    boolean dpad_down_previous_2 = false;
    boolean dpad_left_previous_2 = false;
    boolean dpad_right_previous_2 = false;
    boolean a_previous_2 = false;
    boolean b_previous_2 = false;
    boolean x_previous_2 = false;
    boolean y_previous_2 = false;
    boolean guide_previous_2 = false;
    boolean start_previous_2 = false;
    boolean back_previous_2 = false;
    boolean left_bumper_previous_2 = false;
    boolean right_bumper_previous_2 = false;
    boolean left_stick_button_previous_2 = false;
    boolean right_stick_button_previous_2 = false;

    float left_stick_x_previous_2 = 0.0F;
    float left_stick_y_previous_2 = 0.0F;
    float right_stick_x_previous_2 = 0.0F;
    float right_stick_y_previous_2 = 0.0F;

    float left_trigger_previous_2 = 0.0F;
    float right_trigger_previous_2 = 0.0F;


    ControllerEventProcessor g;

    OpMode parent;

    public EventGamepad(OpMode parent) { this.parent = parent;}

    public EventGamepad(OpMode parent, ControllerEventProcessor g) {
        this.g = g;
        this.parent = parent;
    }

    public void setInputProcessor(ControllerEventProcessor g) {
        this.g = g;
    }

    public void loop() {
        surveyGamepad1();
        surveyGamepad2();
    }

    private void surveyGamepad1() {
        if( parent.gamepad1.dpad_up && !dpad_up_previous_1)
            g.buttonDown( 1, Buttons.dpad_up);
        if(! parent.gamepad1.dpad_up && dpad_up_previous_1)
            g.buttonUp( 1, Buttons.dpad_up);
        dpad_up_previous_1 = parent.gamepad1.dpad_up;

        if( parent.gamepad1.dpad_down && !dpad_down_previous_1)
            g.buttonDown( 1, Buttons.dpad_down);
        if(! parent.gamepad1.dpad_down && dpad_down_previous_1)
            g.buttonUp( 1, Buttons.dpad_down);
        dpad_down_previous_1 = parent.gamepad1.dpad_down;

        if( parent.gamepad1.dpad_left && !dpad_left_previous_1)
            g.buttonDown( 1, Buttons.dpad_left);
        if(! parent.gamepad1.dpad_left && dpad_left_previous_1)
            g.buttonUp( 1, Buttons.dpad_left);
        dpad_left_previous_1 = parent.gamepad1.dpad_left;

        if( parent.gamepad1.dpad_right && !dpad_right_previous_1)
            g.buttonDown( 1, Buttons.dpad_right);
        if(! parent.gamepad1.dpad_right && dpad_right_previous_1)
            g.buttonUp( 1, Buttons.dpad_right);
        dpad_right_previous_1 = parent.gamepad1.dpad_right;

        if( parent.gamepad1.a && !a_previous_1)
            g.buttonDown( 1, Buttons.a);
        if(! parent.gamepad1.a && a_previous_1)
            g.buttonUp( 1, Buttons.a);
        a_previous_1 = parent.gamepad1.a;

        if( parent.gamepad1.b && !b_previous_1)
            g.buttonDown( 1, Buttons.b);
        if(! parent.gamepad1.b && b_previous_1)
            g.buttonUp( 1, Buttons.b);
        b_previous_1 = parent.gamepad1.b;

        if( parent.gamepad1.x && !x_previous_1)
            g.buttonDown( 1, Buttons.x);
        if(! parent.gamepad1.x && x_previous_1)
            g.buttonUp( 1, Buttons.x);
        x_previous_1 = parent.gamepad1.x;

        if( parent.gamepad1.y && !y_previous_1)
            g.buttonDown( 1, Buttons.y);
        if(! parent.gamepad1.y && y_previous_1)
            g.buttonUp( 1, Buttons.y);
        y_previous_1 = parent.gamepad1.y;

        if( parent.gamepad1.guide && !guide_previous_1)
            g.buttonDown( 1, Buttons.guide);
        if(! parent.gamepad1.guide && guide_previous_1)
            g.buttonUp( 1, Buttons.guide);
        guide_previous_1 = parent.gamepad1.guide;

        if( parent.gamepad1.start && !start_previous_1)
            g.buttonDown( 1, Buttons.start);
        if(! parent.gamepad1.start && start_previous_1)
            g.buttonUp( 1, Buttons.start);
        start_previous_1 = parent.gamepad1.start;

        if( parent.gamepad1.back && !back_previous_1)
            g.buttonDown( 1, Buttons.back);
        if(! parent.gamepad1.back && back_previous_1)
            g.buttonUp( 1, Buttons.back);
        back_previous_1 = parent.gamepad1.back;

        if( parent.gamepad1.left_bumper && !left_bumper_previous_1)
            g.buttonDown( 1, Buttons.left_bumper);
        if(! parent.gamepad1.left_bumper && left_bumper_previous_1)
            g.buttonUp( 1, Buttons.left_bumper);
        left_bumper_previous_1 = parent.gamepad1.left_bumper;

        if( parent.gamepad1.right_bumper && !right_bumper_previous_1)
            g.buttonDown( 1, Buttons.right_bumper);
        if(! parent.gamepad1.right_bumper && right_bumper_previous_1)
            g.buttonUp( 1, Buttons.right_bumper);
        right_bumper_previous_1 = parent.gamepad1.right_bumper;

        if( parent.gamepad1.left_stick_button && !left_stick_button_previous_1)
            g.buttonDown( 1, Buttons.left_stick_button);
        if(! parent.gamepad1.left_stick_button && left_stick_button_previous_1)
            g.buttonUp( 1, Buttons.left_stick_button);
        left_stick_button_previous_1 = parent.gamepad1.left_stick_button;

        if( parent.gamepad1.right_stick_button && !right_stick_button_previous_1)
            g.buttonDown( 1, Buttons.right_stick_button);
        if(! parent.gamepad1.right_stick_button && right_stick_button_previous_1)
            g.buttonUp( 1, Buttons.right_stick_button);
        right_stick_button_previous_1 = parent.gamepad1.right_stick_button;

        if( parent.gamepad1.left_stick_x != left_stick_x_previous_1 || parent.gamepad1.left_stick_y != left_stick_y_previous_1)
            g.joystickMoved( 1, Buttons.left_stick, parent.gamepad1.left_stick_x, parent.gamepad1.left_stick_y);
        left_stick_x_previous_1 = parent.gamepad1.left_stick_x;
        left_stick_y_previous_1 = parent.gamepad1.left_stick_y;

        if( parent.gamepad1.right_stick_x != right_stick_x_previous_1 || parent.gamepad1.right_stick_y != right_stick_y_previous_1)
            g.joystickMoved( 1, Buttons.right_stick, parent.gamepad1.right_stick_x, parent.gamepad1.right_stick_y);
        right_stick_x_previous_1 = parent.gamepad1.right_stick_x;
        right_stick_y_previous_1 = parent.gamepad1.right_stick_y;

        if( parent.gamepad1.left_trigger != left_trigger_previous_1)
            g.triggerPressed( 1, Buttons.left_trigger, parent.gamepad1.left_trigger);
        left_trigger_previous_1 = parent.gamepad1.left_trigger;

        if( parent.gamepad1.right_trigger != right_trigger_previous_1)
            g.triggerPressed( 1, Buttons.right_trigger, parent.gamepad1.right_trigger);
        right_trigger_previous_1 = parent.gamepad1.right_trigger;
    }

    private void surveyGamepad2() {
        if( parent.gamepad2.dpad_up && !dpad_up_previous_2)
            g.buttonDown( 2, Buttons.dpad_up);
        if(! parent.gamepad2.dpad_up && dpad_up_previous_2)
            g.buttonUp( 2, Buttons.dpad_up);
        dpad_up_previous_2 = parent.gamepad2.dpad_up;

        if( parent.gamepad2.dpad_down && !dpad_down_previous_2)
            g.buttonDown( 2, Buttons.dpad_down);
        if(! parent.gamepad2.dpad_down && dpad_down_previous_2)
            g.buttonUp( 2, Buttons.dpad_down);
        dpad_down_previous_2 = parent.gamepad2.dpad_down;

        if( parent.gamepad2.dpad_left && !dpad_left_previous_2)
            g.buttonDown( 2, Buttons.dpad_left);
        if(! parent.gamepad2.dpad_left && dpad_left_previous_2)
            g.buttonUp( 2, Buttons.dpad_left);
        dpad_left_previous_2 = parent.gamepad2.dpad_left;

        if( parent.gamepad2.dpad_right && !dpad_right_previous_2)
            g.buttonDown( 2, Buttons.dpad_right);
        if(! parent.gamepad2.dpad_right && dpad_right_previous_2)
            g.buttonUp( 2, Buttons.dpad_right);
        dpad_right_previous_2 = parent.gamepad2.dpad_right;

        if( parent.gamepad2.a && !a_previous_2)
            g.buttonDown( 2, Buttons.a);
        if(! parent.gamepad2.a && a_previous_2)
            g.buttonUp( 2, Buttons.a);
        a_previous_2 = parent.gamepad2.a;

        if( parent.gamepad2.b && !b_previous_2)
            g.buttonDown( 2, Buttons.b);
        if(! parent.gamepad2.b && b_previous_2)
            g.buttonUp( 2, Buttons.b);
        b_previous_2 = parent.gamepad2.b;

        if( parent.gamepad2.x && !x_previous_2)
            g.buttonDown( 2, Buttons.x);
        if(! parent.gamepad2.x && x_previous_2)
            g.buttonUp( 2, Buttons.x);
        x_previous_2 = parent.gamepad2.x;

        if( parent.gamepad2.y && !y_previous_2)
            g.buttonDown( 2, Buttons.y);
        if(! parent.gamepad2.y && y_previous_2)
            g.buttonUp( 2, Buttons.y);
        y_previous_2 = parent.gamepad2.y;

        if( parent.gamepad2.guide && !guide_previous_2)
            g.buttonDown( 2, Buttons.guide);
        if(! parent.gamepad2.guide && guide_previous_2)
            g.buttonUp( 2, Buttons.guide);
        guide_previous_2 = parent.gamepad2.guide;

        if( parent.gamepad2.start && !start_previous_2)
            g.buttonDown( 2, Buttons.start);
        if(! parent.gamepad2.start && start_previous_2)
            g.buttonUp( 2, Buttons.start);
        start_previous_2 = parent.gamepad2.start;

        if( parent.gamepad2.back && !back_previous_2)
            g.buttonDown( 2, Buttons.back);
        if(! parent.gamepad2.back && back_previous_2)
            g.buttonUp( 2, Buttons.back);
        back_previous_2 = parent.gamepad2.back;

        if( parent.gamepad2.left_bumper && !left_bumper_previous_2)
            g.buttonDown( 2, Buttons.left_bumper);
        if(! parent.gamepad2.left_bumper && left_bumper_previous_2)
            g.buttonUp( 2, Buttons.left_bumper);
        left_bumper_previous_2 = parent.gamepad2.left_bumper;

        if( parent.gamepad2.right_bumper && !right_bumper_previous_2)
            g.buttonDown( 2, Buttons.right_bumper);
        if(! parent.gamepad2.right_bumper && right_bumper_previous_2)
            g.buttonUp( 2, Buttons.right_bumper);
        right_bumper_previous_2 = parent.gamepad2.right_bumper;

        if( parent.gamepad2.left_stick_button && !left_stick_button_previous_2)
            g.buttonDown( 2, Buttons.left_stick_button);
        if(! parent.gamepad2.left_stick_button && left_stick_button_previous_2)
            g.buttonUp( 2, Buttons.left_stick_button);
        left_stick_button_previous_2 = parent.gamepad2.left_stick_button;

        if( parent.gamepad2.right_stick_button && !right_stick_button_previous_2)
            g.buttonDown( 2, Buttons.right_stick_button);
        if(! parent.gamepad2.right_stick_button && right_stick_button_previous_2)
            g.buttonUp( 2, Buttons.right_stick_button);
        right_stick_button_previous_2 = parent.gamepad2.right_stick_button;

        if( parent.gamepad2.left_stick_x != left_stick_x_previous_2 || parent.gamepad2.left_stick_y != left_stick_y_previous_2)
            g.joystickMoved( 2, Buttons.left_stick, parent.gamepad2.left_stick_x, parent.gamepad2.left_stick_y);
        left_stick_x_previous_2 = parent.gamepad2.left_stick_x;
        left_stick_y_previous_2 = parent.gamepad2.left_stick_y;

        if( parent.gamepad2.right_stick_x != right_stick_x_previous_2 || parent.gamepad2.right_stick_y != right_stick_y_previous_2)
            g.joystickMoved( 2, Buttons.right_stick, parent.gamepad2.right_stick_x, parent.gamepad2.right_stick_y);
        right_stick_x_previous_2 = parent.gamepad2.right_stick_x;
        right_stick_y_previous_2 = parent.gamepad2.right_stick_y;

        if( parent.gamepad2.left_trigger != left_trigger_previous_2)
            g.triggerPressed( 2, Buttons.left_trigger, parent.gamepad2.left_trigger);
        left_trigger_previous_2 = parent.gamepad2.left_trigger;

        if( parent.gamepad2.right_trigger != right_trigger_previous_2)
            g.triggerPressed( 2, Buttons.right_trigger, parent.gamepad2.right_trigger);
        right_trigger_previous_2 = parent.gamepad2.right_trigger;
    }
}

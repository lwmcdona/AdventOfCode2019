package day7;

import day2.Intcode;
import day2.Intcode.ExitCode;

public class Amplifier {
    private Intcode computer;
    private Connection input;
    private Connection output;
    private ExitCode status;

    public Amplifier(String icode, Connection input, Connection output) {
        this.input = input;
        this.output = output;
        this.computer = new Intcode(icode, input, output);
        resetStatus();
    }

    public void reset() {
        this.computer.reset();
        this.input.reset();
        resetStatus();
    }

    public void run() {
        this.status = this.computer.compute();
    }

    public boolean canRun() {
        return this.status == ExitCode.WAIT_FOR_INPUT && !this.input.isEmpty();
    }

    public void send(Integer value) {
        this.input.send(value);
    }

    public int receive() {
        return this.output.receive();
    }

    private void resetStatus() {
        this.status = ExitCode.WAIT_FOR_INPUT;
    }

}

package br.pro.hashi.ensino.desagil.aps.model;

public class FullAdler extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandD;
    private final NandGate nandE;
    private final NandGate nandF;
    private final NandGate nandG;
    private final NandGate nandSum;
    private final NandGate nandCarry;

    public FullAdler() {
        super("FullAdler", 3, 2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandD = new NandGate();
        nandE = new NandGate();
        nandF = new NandGate();
        nandG = new NandGate();
        nandSum = new NandGate();
        nandCarry = new NandGate();

        nandB.connect(1, nandA);

        nandC.connect(0, nandA);

        nandD.connect(0, nandB);
        nandD.connect(1, nandC);

        nandE.connect(0, nandD);

        nandF.connect(0, nandD);
        nandF.connect(1, nandE);

        nandG.connect(0, nandE);

        nandSum.connect(0, nandF);
        nandSum.connect(1, nandG);

        nandCarry.connect(0, nandE);
        nandCarry.connect(1, nandA);

    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandSum.read();
        }

        if (outputPin == 1) {
            return nandCarry.read();
        }

        else {
            throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandB.connect(0, emitter);
                nandA.connect(1, emitter);
                break;
            case 1:
                nandA.connect(0, emitter);
                nandC.connect(1, emitter);
                break;
            case 2:
                nandE.connect(1, emitter);
                nandG.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandA;
    private final NandGate nandB;
    private final NandGate nandC;
    private final NandGate nandoutA;
    private final NandGate nandoutB;

    public DeMux() {
        super("DeMux", 2, 2);
        nandA = new NandGate();
        nandB = new NandGate();
        nandC = new NandGate();
        nandoutA = new NandGate();
        nandoutB = new NandGate();

        nandB.connect(1, nandA);

        nandoutA.connect(0, nandB);
        nandoutA.connect(1, nandB);

        nandoutB.connect(0, nandC);
        nandoutB.connect(1, nandC);

    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandoutA.read();
        }

        if (outputPin == 1) {
            return nandoutB.read();
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
                nandC.connect(1, emitter);
                break;
            case 1:
                nandA.connect(0, emitter);
                nandA.connect(1, emitter);
                nandC.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

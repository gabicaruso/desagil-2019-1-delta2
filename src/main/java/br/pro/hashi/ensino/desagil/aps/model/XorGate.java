package br.pro.hashi.ensino.desagil.aps.model;

@SuppressWarnings("ALL")
public class XorGate extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;


    public XorGate() {
        super("XOR", 2, 1);

        nandLeft = new NandGate();
        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom   =   new NandGate();
        nandBottom.connect(0, nandLeft);

        nandRight = new NandGate();

        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}

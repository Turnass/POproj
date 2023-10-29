package xxl.core;

/**
 * An abstract class representing operations over a gamma (subset of cells).
 * Extends the Operation class and implements the Observer interface.
 */
public abstract class GamaOperation extends Operation implements Observer {

    private Gamma _gamma;
    private boolean _hasChanged = true;

    /**
     * Method to update the change status when notified by an observer.
     */
    @Override
    public void update() {
        _hasChanged = true;
    }

    /**
     * Returns the change status of the operation.
     *
     * @return A boolean indicating if the operation has changed.
     */
    public boolean getHasChanged() {
        return _hasChanged;
    }

    /**
     * Sets the change status of the operation.
     *
     * @param state A boolean value to set the changed state of the operation.
     */
    public void setHasChanged(boolean state) {
        _hasChanged = state;
    }

    /**
     * Adds observers to the gamma of this operation.
     */
    public void addObservers() {
        _gamma.addObserver(this);
    }

    /**
     * Returns the gamma argument of the operation.
     *
     * @return The gamma argument for the operation.
     */
    public Gamma getArg() {
        return _gamma;
    }

    /**
     * Sets the gamma argument of the operation.
     *
     * @param gamma The gamma to be set as the operation's argument.
     */
    public void setArg(Gamma gamma) {
        _gamma = gamma;
    }

    /**
     * Generates a string representation of the GamaOperation.
     *
     * @return A string showing the operation and the gamma it operates on.
     */
    public String toString() {
        return "=" + getOperationName() + "(" + _gamma.toString() + ")";
    }
}

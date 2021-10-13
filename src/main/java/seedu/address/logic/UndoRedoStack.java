package seedu.address.logic;

import java.util.Stack;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UndoableCommand;


/**
 * Keeps track of Commands that have been called (undoStack)
 * and Commands that have been undone (redoStack).
 */
public class UndoRedoStack {
    private Stack<UndoableCommand> undoStack;
    private Stack<UndoableCommand> redoStack;

    /**
     * Constructor for UndoRedoStack to store Undoable commands
     */
    public UndoRedoStack() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Adds Command to Undo Stack if command is undoable.
     * @param command Command executed.
     */
    public void pushUndoableCommand(Command command) {
        if (!(command instanceof UndoableCommand)) {
            return;
        }

        undoStack.push((UndoableCommand) command);
    }

    /**
     * @return If there are commands to be undone.
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    /**
     * Gets the last executed UndoableCommand.
     * @return UndoableCommand
     */
    public UndoableCommand popUndo() {
        UndoableCommand toUndo = undoStack.pop();
        redoStack.push(toUndo);
        return toUndo;
    }

    /**
     * Checks if there are any commands that can be redone.
     * @return If there are commands to be redone.
     */
    public boolean canRedo() {
        return !redoStack.empty();
    }

    /**
     * Command that has been redone will be moved back to the undoStack so that command can be undone again.
     */
    public UndoableCommand popRedo() {
        UndoableCommand toRedo = redoStack.pop();
        undoStack.push(toRedo);
        return toRedo;
    }
}

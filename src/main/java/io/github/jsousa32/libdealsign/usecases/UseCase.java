package io.github.jsousa32.libdealsign.usecases;

public abstract class UseCase<OUT, IN> {

    public abstract OUT execute(IN anIn);
}

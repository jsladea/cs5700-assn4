package org.example.instructions

abstract class Instruction {
    abstract fun execute(cpu: CPU, op1: Byte, op2: Byte)
}
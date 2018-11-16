package com.mayhem.rs2.entity.player.net.out.impl;

import com.mayhem.core.network.StreamBuffer;
import com.mayhem.rs2.entity.player.net.Client;
import com.mayhem.rs2.entity.player.net.out.OutgoingPacket;

public class SendSpecial extends OutgoingPacket {

	private final int amount;

	public SendSpecial(int amount) {
		super();
		this.amount = amount;
	}

	@Override
	public void execute(Client client) {
		StreamBuffer.OutBuffer out = StreamBuffer.newOutBuffer(8);
		out.writeHeader(client.getEncryptor(), 77);
		out.writeInt(amount);
		client.send(out.getBuffer());
	}

	@Override
	public int getOpcode() {
		return 77;
	}

}

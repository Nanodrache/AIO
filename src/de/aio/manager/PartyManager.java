package de.aio.manager;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import de.aio.AIO;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class PartyManager
{
	private HashMap<Long, String> lobbyList = new HashMap<>();
	private HashMap<Long, Long> partyList = new HashMap<>();
	
	public void createLobby(VoiceChannel vc, String partyName)
	{
		lobbyList.put(vc.getIdLong(), partyName);
	}

	public boolean isLobby(long id)
	{
		return lobbyList.containsKey(id);
	}

	public boolean isParty(long id)
	{
		return partyList.containsKey(id);
	}

	public void createParty(VoiceChannel vcLobby, Member member)
	{
		VoiceChannel vcParty = vcLobby.getParent().createVoiceChannel(lobbyList.get(vcLobby.getIdLong()) + " 0").complete();
		
		int partyCounter = 0;
		
		for (long id : partyList.values())
		{
			if (id == vcLobby.getIdLong()) partyCounter++;
		}

		vcParty.getManager().setBitrate(vcLobby.getBitrate());
		vcParty.getManager().setName(lobbyList.get(vcLobby.getIdLong()) + " " + (partyCounter + 1));
		vcParty.getManager().setPosition((vcLobby.getPosition() + partyCounter + 1));
		vcParty.getManager().setUserLimit(vcLobby.getUserLimit());
		
		vcLobby.getGuild().moveVoiceMember(member, vcParty);
		
		partyList.put(vcParty.getIdLong(), vcLobby.getIdLong());
	}

	public void deleteLobby(long id)
	{
		for (long idParty : partyList.keySet())
		{
			VoiceChannel vcParty = AIO.INSTANCE.jda.getVoiceChannelById(idParty);
			if (vcParty.getName().startsWith(lobbyList.get(id))) vcParty.delete().complete();
		}
		
		lobbyList.remove(id);
	}

	public void deleteParty(VoiceChannel vc)
	{
		vc.delete().delay(10, TimeUnit.SECONDS);
		partyList.remove(vc.getIdLong());
	}

	public void deleteParty(long id)
	{
		AIO.INSTANCE.jda.getVoiceChannelById(id).delete().delay(10, TimeUnit.SECONDS);
		partyList.remove(id);
	}
}

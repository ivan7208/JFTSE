package com.ft.emulator.server.database.repository.player;

import com.ft.emulator.server.database.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByName(String name);
    List<Player> findAllByName(String name);

    @Query(value = "FROM Player p "
            + "LEFT JOIN FETCH p.account account "
            + "LEFT JOIN FETCH p.pocket pocket "
            + "LEFT JOIN FETCH p.clothEquipment clothEquipment "
            + "LEFT JOIN FETCH p.quickSlotEquipment quickSlotEquipment "
            + "WHERE p.id = :playerId")
    Optional<Player> findByIdFetched(@Param("playerId") Long playerId);
}

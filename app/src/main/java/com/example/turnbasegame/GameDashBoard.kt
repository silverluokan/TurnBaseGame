package com.example.turnbasegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.turnbasegame.Constants.PlayerName
import com.example.turnbasegame.Enemy.Enemy_Bat
import com.example.turnbasegame.Enemy.Enemy_Rat
import com.example.turnbasegame.Enemy.Enemy_bslime
import com.example.turnbasegame.databinding.ActivityGameDashBoardBinding

class GameDashBoard : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGameDashBoardBinding
    var turn = 1
    var EnemyCurrentHp = 0
    var EnemyDamage = 0
    var EnemyHeal = 0
    var PlayerFinalDamage = 0
    var EnemyFinalDamage = 0
    var EnemyDefense = 0
    var EnemyName = ""
    var EnemyExp = 0

    var PlayerCurrentHp = 0
    var playerMaxHp = 0
    var EnemyMaxHp = 0
    var PlayerDamage = 0
    var PlayerDefense = 0
    var PlayerHeal = 0
    var PlayerStats = ""
    var PlayerLevel = 0
    var PlayerExpNeeded = 10
    var PlayerExp = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = Player("UserInput")
        binding.PlayerName.text = player.Name
        PlayerCurrentHp = player.MaxHealthPoint
        playerMaxHp = player.MaxHealthPoint
        binding.playerhp.progress = (PlayerCurrentHp/player.MaxHealthPoint)*100
        binding.txtPlayerHp.text = "$PlayerCurrentHp/${player.MaxHealthPoint}"
        PlayerDamage = player.Damage
        PlayerHeal = player.Heal
        PlayerDefense = player.DefensePoint
        PlayerLevel = player.Level
        CharacterExp()

        binding.btnRoll.isEnabled = false
        binding.btnAttack.isEnabled = false
        binding.btnDefend.isEnabled = false
        binding.btnHeal.isEnabled = false

        binding.btnFindEnemy.setOnClickListener(this)
        binding.btnRoll.setOnClickListener(this)
        binding.btnAttack.setOnClickListener(this)
        binding.btnDefend.setOnClickListener(this)
        binding.btnHeal.setOnClickListener(this)
        binding.btnAgain.setOnClickListener(this)
    }



    fun EnemyDo(){
        val rand = (1..3).random()
        when(rand){
            1->{
                if(PlayerStats == "Attack"){
                    EnemyCurrentHp -= PlayerFinalDamage
                    PlayerCurrentHp -= (EnemyDamage * enemyRoll)
                }else if(PlayerStats == "Defend"){
                    PlayerCurrentHp = (PlayerCurrentHp+(PlayerDefense*playerRoll)) - (EnemyDamage * enemyRoll)
                    if(PlayerCurrentHp > playerMaxHp){
                        PlayerCurrentHp = playerMaxHp
                    }
                }else{
                    PlayerCurrentHp += PlayerHeal * playerRoll
                    if(PlayerCurrentHp > playerMaxHp){
                        PlayerCurrentHp = playerMaxHp
                    }
                    PlayerCurrentHp -= (EnemyDamage * enemyRoll)
                }
            }
            2->{
                if(PlayerStats == "Attack"){
                    EnemyCurrentHp = (EnemyCurrentHp+(EnemyDefense*enemyRoll)) - PlayerFinalDamage
                    if(EnemyCurrentHp > EnemyMaxHp){
                        EnemyCurrentHp = EnemyMaxHp
                    }
                }else if(PlayerStats == "Defend"){

                }else{
                    PlayerCurrentHp += PlayerHeal*playerRoll
                    if(PlayerCurrentHp > playerMaxHp){
                        PlayerCurrentHp = playerMaxHp
                    }
                }
            }
            3->{
                if(PlayerStats == "Attack"){
                    EnemyCurrentHp += EnemyHeal*enemyRoll
                    if(EnemyCurrentHp > EnemyMaxHp){
                        EnemyCurrentHp = EnemyMaxHp
                    }
                    EnemyCurrentHp -= PlayerFinalDamage
                }else if(PlayerStats == "Defend"){
                    EnemyCurrentHp += EnemyHeal*enemyRoll
                    if(EnemyCurrentHp > EnemyMaxHp){
                        EnemyCurrentHp = EnemyMaxHp
                    }
                }else{
                    EnemyCurrentHp += EnemyHeal*enemyRoll
                    if(EnemyCurrentHp > EnemyMaxHp){
                        EnemyCurrentHp = EnemyMaxHp
                    }
                    PlayerCurrentHp += PlayerHeal*playerRoll
                    if(PlayerCurrentHp > playerMaxHp){
                        PlayerCurrentHp = playerMaxHp
                    }
                }
            }
        }
    }

    fun death(){
        binding.btnRoll.isEnabled = false
        binding.btnAttack.isEnabled = false
        binding.btnDefend.isEnabled = false
        binding.btnHeal.isEnabled = false
        binding.btnFindEnemy.isEnabled = true
    }

    fun FindEnemy(){
        val enemyFind = (1..3).random()
        when(enemyFind){
            1->{
                val enemy = Enemy_Bat()
                EnemyCurrentHp = enemy.MaxHealthPoint
                EnemyDamage = enemy.Damage
                EnemyDefense = enemy.DefensePoint
                EnemyName = enemy.Name
                EnemyExp = enemy.Exp
                EnemyHeal = enemy.Heal
                EnemyMaxHp = enemy.MaxHealthPoint
                binding.enemyImage.setImageResource(R.drawable.bat)
                binding.txtEnemyHp.text = "$EnemyCurrentHp/${enemy.MaxHealthPoint}"
                binding.enemyhp.progress = (EnemyCurrentHp/enemy.MaxHealthPoint)*100
                binding.btnFindEnemy.isEnabled = false
            }
            2->{
                val enemy = Enemy_bslime()
                EnemyCurrentHp = enemy.MaxHealthPoint
                EnemyDamage = enemy.Damage
                EnemyDefense = enemy.DefensePoint
                EnemyName = enemy.Name
                EnemyExp = enemy.Exp
                EnemyHeal = enemy.Heal
                EnemyMaxHp = enemy.MaxHealthPoint
                binding.enemyImage.setImageResource(R.drawable.slime)
                binding.txtEnemyHp.text = "$EnemyCurrentHp/${enemy.MaxHealthPoint}"
                binding.enemyhp.progress = (EnemyCurrentHp/enemy.MaxHealthPoint)*100
                binding.btnFindEnemy.isEnabled = false
            }
            3->{
                val enemy = Enemy_Rat()
                EnemyCurrentHp = enemy.MaxHealthPoint
                EnemyDamage = enemy.Damage
                EnemyDefense = enemy.DefensePoint
                EnemyName = enemy.Name
                EnemyExp = enemy.Exp
                EnemyHeal = enemy.Heal
                EnemyMaxHp = enemy.MaxHealthPoint
                binding.enemyImage.setImageResource(R.drawable.rat)
                binding.txtEnemyHp.text = "$EnemyCurrentHp/${enemy.MaxHealthPoint}"
                binding.enemyhp.progress = (EnemyCurrentHp/enemy.MaxHealthPoint)*100
                binding.btnFindEnemy.isEnabled = false
            }
        }
        binding.btnRoll.isEnabled = true
    }

    var enemyRoll = 0
    var playerRoll = 0

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btnFindEnemy)->{
                FindEnemy()
                PlayerCurrentHp = playerMaxHp
                reprint()
                CharacterExp()
            }
            (R.id.btnRoll)->{
                enemyRoll = (0..6).random()
                playerRoll = (0..6).random()
                binding.txtenemyRoll.text = enemyRoll.toString()
                binding.txtplayerRoll.text = playerRoll.toString()
                binding.btnRoll.isEnabled = false
                binding.btnAttack.isEnabled = true
                binding.btnDefend.isEnabled = true
                binding.btnHeal.isEnabled = true
            }
            (R.id.btnAttack)->{
                PlayerFinalDamage += (PlayerDamage * playerRoll)
                PlayerStats = "Attack"
                EnemyDo()
                checker()
                roll()
                CharacterExp()
                reprint()
            }
            (R.id.btnDefend)->{
                PlayerStats = "Defend"
                EnemyDo()
                checker()
                roll()
                CharacterExp()
                reprint()
            }
            (R.id.btnHeal)->{
                PlayerStats = "Heal"
                EnemyDo()
                checker()
                roll()
                CharacterExp()
                reprint()
            }
            (R.id.btnAgain)->{
                val player = Player("UserInput")
                player.Level = 1
                finish()
                startActivity(intent)
            }
        }
    }
    fun reprint(){
        var ProgPlayer = 100.00*(PlayerCurrentHp.toDouble()/playerMaxHp.toDouble())
        Log.d("PlayerProgress", ProgPlayer.toString())
        binding.playerhp.setProgress(ProgPlayer.toInt())
        binding.txtPlayerHp.text = "$PlayerCurrentHp/$playerMaxHp"
        var ProgEnemy = 100.00*(EnemyCurrentHp.toDouble()/EnemyMaxHp.toDouble())
        Log.d("EnemyProgress", ProgEnemy.toString())
        binding.txtEnemyHp.text = "$EnemyCurrentHp/$EnemyMaxHp"
        binding.enemyhp.setProgress(ProgEnemy.toInt())

        PlayerFinalDamage = 0
        EnemyFinalDamage = 0
    }
    fun checker(){
        if(EnemyCurrentHp <= 0){
            binding.enemyImage.setImageResource(R.drawable.tomb)
            binding.txtEnemyHp.text = "0/$EnemyMaxHp"
            binding.enemyhp.progress = 0
            death()
            binding.btnFindEnemy.isEnabled = true
        }
        if(PlayerCurrentHp <= 0){
            binding.playerhp.progress = 0
            binding.txtPlayerHp.text = "0/$playerMaxHp"
            binding.playerImage.setImageResource(R.drawable.tomb)
            death()
            binding.btnAgain.visibility = View.VISIBLE
        }
    }
    fun roll(){
        binding.btnRoll.isEnabled = true
        binding.btnAttack.isEnabled = false
        binding.btnDefend.isEnabled = false
        binding.btnHeal.isEnabled = false
    }

    fun CharacterExp(){
        val player = Player("UserInput")
        if(EnemyName != "") {
            if (EnemyCurrentHp <= 0) {
                PlayerExp += EnemyExp
                if (PlayerExp > PlayerExpNeeded) {
                    if ((PlayerExp - PlayerExpNeeded) == 0) {
                        player.Level = player.Level + 1
                        binding.PlayerLevel.text = "Level: ${player.Level}"
                        PlayerExp = 0
                        PlayerExpNeeded *= 2
                        PlayerLevel = player.Level
                    } else {
                        player.Level = player.Level + 1
                        binding.PlayerLevel.text = "Level: ${player.Level}"
                        PlayerExp -= PlayerExpNeeded
                        PlayerExpNeeded *= 2
                        PlayerLevel = player.Level
                    }
                }
            }
        }
        Log.d("Player Exp: ", PlayerExp.toString())
        binding.PlayerLevel.text = "Level: $PlayerLevel"
    }
}
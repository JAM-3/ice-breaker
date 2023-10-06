package demoMod.icebreaker.cards.lightlemon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import demoMod.icebreaker.IceBreaker;

public class TriggerLightning extends AbstractLightLemonCard {
    public static final String ID = IceBreaker.makeID("TriggerLightning");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "cards/TriggerLightning.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    private static final int COST = 0;

    public TriggerLightning() {
        super(ID, NAME, IceBreaker.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.isFetter = true;
        this.tags.add(CardTags.HEALING);
        this.fetterFilter = card -> !card.uuid.equals(this.uuid);
        this.exhaust = true;
        this.isInnate = true;
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
        if (AbstractDungeon.player == null) return;
        if (AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) return;
        this.fetterAmount++;
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.uuid.equals(this.uuid)) {
                onAddToMasterDeck();
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}

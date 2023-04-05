package demoMod.icebreaker.cards.lightlemon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import demoMod.icebreaker.IceBreaker;
import demoMod.icebreaker.cards.lightlemon.tempCards.Spark;
import demoMod.icebreaker.powers.TimeStasisPower;

public class Electrospark extends AbstractLightLemonCard {
    public static final String ID = IceBreaker.makeID("Electrospark");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "cards/Electrospark.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 2;

    public Electrospark() {
        super(ID, NAME, IceBreaker.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, RARITY, TARGET);
        this.cardsToPreview = new Spark();
        this.selfRetain = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int ctr = (int) AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(card -> card instanceof Spark).count();
        if (ctr > 12) {
            ctr = 12;
        }
        addToBot(new ApplyPowerAction(p, p, new TimeStasisPower(p, ctr)));
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = (int) AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(card -> card instanceof Spark).count();
        if (this.baseMagicNumber > 12) {
            this.baseMagicNumber = 12;
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
}

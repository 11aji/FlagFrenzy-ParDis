import java.util.ArrayList;
import java.util.List;

public class FlagDatabase {
    private List<Flag> easyFlags, mediumFlags, hardFlags;

    public FlagDatabase() {
        easyFlags = new ArrayList<>();
        mediumFlags = new ArrayList<>();
        hardFlags = new ArrayList<>();
        loadFlags();
    }

    private void loadFlags() {
        // Placeholder for actual flag loading logic
        // Easy flags
        easyFlags.add(new Flag("USA", "images\\easy\\us.png"));
        easyFlags.add(new Flag("Canada", "images\\easy\\ca.png"));
        easyFlags.add(new Flag("South Korea", "images\\easy\\kr.png"));
        easyFlags.add(new Flag("Japan", "images\\easy\\jp.png"));
        easyFlags.add(new Flag("Indonesia", "images\\easy\\id.png"));
        easyFlags.add(new Flag("Brazil", "images\\easy\\br.png"));
        easyFlags.add(new Flag("Poland", "images\\easy\\pl.png"));
        easyFlags.add(new Flag("Denmark", "images\\easy\\dk.png"));
        easyFlags.add(new Flag("Germany", "images\\easy\\de.png"));
        easyFlags.add(new Flag("Israel", "images\\easy\\il.png"));
        easyFlags.add(new Flag("Philippines", "images\\easy\\ph.png"));
        easyFlags.add(new Flag("Pakistan", "images\\easy\\pk.png"));
        easyFlags.add(new Flag("Vietnam", "images\\easy\\vn.png"));
        easyFlags.add(new Flag("Switzerland", "images\\easy\\ch.png"));
        easyFlags.add(new Flag("India", "images\\easy\\in.png"));
        easyFlags.add(new Flag("Nepal", "images\\easy\\np.png"));
        easyFlags.add(new Flag("Finland", "images\\easy\\fi.png"));
        easyFlags.add(new Flag("Jamaica", "images\\easy\\jm.png"));
        easyFlags.add(new Flag("Turkey", "images\\easy\\tr.png"));
        easyFlags.add(new Flag("China", "images\\easy\\cn.png"));
        easyFlags.add(new Flag("Australia", "images\\easy\\au.png"));
        easyFlags.add(new Flag("Ukraine", "images\\easy\\ua.png"));
        easyFlags.add(new Flag("Portugal", "images\\easy\\pt.png"));
        easyFlags.add(new Flag("Singapore", "images\\easy\\sg.png"));
        easyFlags.add(new Flag("Palestine", "images\\easy\\ps.png"));
        easyFlags.add(new Flag("North Korea", "images\\easy\\kp.png"));
        easyFlags.add(new Flag("Taiwan", "images\\easy\\tw.png"));
        easyFlags.add(new Flag("Sweden", "images\\easy\\se.png"));
        easyFlags.add(new Flag("United Kingdom", "images\\easy\\gb.png"));
        easyFlags.add(new Flag("Spain", "images\\easy\\es.png"));
        easyFlags.add(new Flag("Greece", "images\\easy\\gr.png"));
        easyFlags.add(new Flag("Qatar", "images\\easy\\qa.png"));
        easyFlags.add(new Flag("Russia", "images\\easy\\ru.png"));
        easyFlags.add(new Flag("Thailand", "images\\easy\\th.png"));
        easyFlags.add(new Flag("Mexico", "images\\easy\\mx.png"));
        easyFlags.add(new Flag("Egypt", "images\\easy\\eg.png"));
        easyFlags.add(new Flag("France", "images\\easy\\fr.png"));
        easyFlags.add(new Flag("Argentina", "images\\easy\\ar.png"));
        easyFlags.add(new Flag("Malaysia", "images\\easy\\my.png"));
        easyFlags.add(new Flag("Cuba", "images\\easy\\cu.png"));
        easyFlags.add(new Flag("Austria", "images\\easy\\at.png"));
        easyFlags.add(new Flag("Laos", "images\\easy\\la.png"));
        easyFlags.add(new Flag("Slovakia", "images\\easy\\sk.png"));

        // Medium flags
        mediumFlags.add(new Flag("Estonia", "images\\medium\\ee.png"));
        mediumFlags.add(new Flag("New Zealand", "images\\medium\\nz.png"));
        mediumFlags.add(new Flag("Iran", "images\\medium\\ir.png"));
        mediumFlags.add(new Flag("Macao", "images\\medium\\mo.png"));
        mediumFlags.add(new Flag("Cambodia", "images\\medium\\kh.png"));
        mediumFlags.add(new Flag("Puerto Rico", "images\\medium\\pr.png"));
        mediumFlags.add(new Flag("Nigeria", "images\\medium\\ng.png"));
        mediumFlags.add(new Flag("Bangladesh", "images\\medium\\bd.png"));
        mediumFlags.add(new Flag("Tanzania", "images\\medium\\tz.png"));
        mediumFlags.add(new Flag("South Africa", "images\\medium\\za.png"));
        mediumFlags.add(new Flag("Kenya", "images\\medium\\ke.png"));
        mediumFlags.add(new Flag("Myanmar", "images\\medium\\mm.png"));
        mediumFlags.add(new Flag("Colombia", "images\\medium\\co.png"));
        mediumFlags.add(new Flag("Uganda", "images\\medium\\ug.png"));
        mediumFlags.add(new Flag("Sudan", "images\\medium\\sd.png"));
        mediumFlags.add(new Flag("Algeria", "images\\medium\\dz.png"));
        mediumFlags.add(new Flag("Iraq", "images\\medium\\iq.png"));
        mediumFlags.add(new Flag("Yemen", "images\\medium\\ye.png"));
        mediumFlags.add(new Flag("Peru", "images\\medium\\pe.png"));
        mediumFlags.add(new Flag("Madagascar", "images\\medium\\mg.png"));
        mediumFlags.add(new Flag("Venezuela", "images\\medium\\ve.png"));
        mediumFlags.add(new Flag("Niger", "images\\medium\\ne.png"));
        mediumFlags.add(new Flag("Mali", "images\\medium\\ml.png"));
        mediumFlags.add(new Flag("Syria", "images\\medium\\sy.png"));
        mediumFlags.add(new Flag("Romania", "images\\medium\\ro.png"));
        mediumFlags.add(new Flag("Chile", "images\\medium\\cl.png"));
        mediumFlags.add(new Flag("Chad", "images\\medium\\td.png"));
        mediumFlags.add(new Flag("Ecuador", "images\\medium\\ec.png"));
        mediumFlags.add(new Flag("Somalia", "images\\medium\\so.png"));
        mediumFlags.add(new Flag("Netherlands", "images\\medium\\nl.png"));
        mediumFlags.add(new Flag("Bolivia", "images\\medium\\bo.png"));
        mediumFlags.add(new Flag("Haiti", "images\\medium\\ht.png"));
        mediumFlags.add(new Flag("Belgium", "images\\medium\\be.png"));
        mediumFlags.add(new Flag("Jordan", "images\\medium\\jo.png"));
        mediumFlags.add(new Flag("Dominican Republic", "images\\medium\\do.png"));
        mediumFlags.add(new Flag("Honduras", "images\\medium\\hn.png"));
        mediumFlags.add(new Flag("Czech Republic", "images\\medium\\cz.png"));
        mediumFlags.add(new Flag("Hungary", "images\\medium\\hu.png"));
        mediumFlags.add(new Flag("Sierra Leone", "images\\medium\\sl.png"));
        mediumFlags.add(new Flag("Serbia", "images\\medium\\rs.png"));
        mediumFlags.add(new Flag("Nicaragua", "images\\medium\\ni.png"));
        mediumFlags.add(new Flag("Libya", "images\\medium\\ly.png"));


        // Hard flags
        hardFlags.add(new Flag("Lithuania", "images\\hard\\lt.png"));
        hardFlags.add(new Flag("Ethiopia", "images\\hard\\et.png"));
        hardFlags.add(new Flag("Congo", "images\\hard\\cg.png"));
        hardFlags.add(new Flag("Morocco", "images\\hard\\ma.png"));
        hardFlags.add(new Flag("Angola", "images\\hard\\ao.png"));
        hardFlags.add(new Flag("Uzbekistan", "images\\hard\\uz.png"));
        hardFlags.add(new Flag("Ghana", "images\\hard\\gh.png"));
        hardFlags.add(new Flag("Mozambique", "images\\hard\\mz.png"));
        hardFlags.add(new Flag("Ivory Coast", "images\\hard\\ci.png"));
        hardFlags.add(new Flag("Cameroon", "images\\hard\\cm.png"));
        hardFlags.add(new Flag("Burkina Faso", "images\\hard\\bf.png"));
        hardFlags.add(new Flag("Sri Lanka", "images\\hard\\lk.png"));
        hardFlags.add(new Flag("Malawi", "images\\hard\\mw.png"));
        hardFlags.add(new Flag("Zambia", "images\\hard\\zm.png"));
        hardFlags.add(new Flag("kazakhstan", "images\\hard\\kz.png"));
        hardFlags.add(new Flag("Guatemala", "images\\hard\\gt.png"));
        hardFlags.add(new Flag("Senegal", "images\\hard\\sn.png"));
        hardFlags.add(new Flag("Zimbabwe", "images\\hard\\zw.png"));
        hardFlags.add(new Flag("Guinea", "images\\hard\\gn.png"));
        hardFlags.add(new Flag("Rwanda", "images\\hard\\rw.png"));
        hardFlags.add(new Flag("Benin", "images\\hard\\bj.png"));
        hardFlags.add(new Flag("Burundi", "images\\hard\\bi.png"));
        hardFlags.add(new Flag("Tunisia", "images\\hard\\tn.png"));
        hardFlags.add(new Flag("South Sudan", "images\\hard\\ss.png"));
        hardFlags.add(new Flag("Azerbaijan", "images\\hard\\az.png"));
        hardFlags.add(new Flag("Papua New Guinea", "images\\hard\\pg.png"));
        hardFlags.add(new Flag("Belarus", "images\\hard\\by.png"));
        hardFlags.add(new Flag("Togo", "images\\hard\\tg.png"));
        hardFlags.add(new Flag("Bhutan", "images\\hard\\bt.png"));
        hardFlags.add(new Flag("Solomon Island", "images\\hard\\sb.png"));
        hardFlags.add(new Flag("Eswatini", "images\\hard\\sz.png"));
        hardFlags.add(new Flag("Guyana", "images\\hard\\gy.png"));
        hardFlags.add(new Flag("Barbados", "images\\hard\\bb.png"));
        hardFlags.add(new Flag("Kiribati", "images\\hard\\ki.png"));
        hardFlags.add(new Flag("Seychelles", "images\\hard\\sc.png"));
        hardFlags.add(new Flag("Antigua and Barbuda", "images\\hard\\ag.png"));
        hardFlags.add(new Flag("Eritrea", "images\\hard\\er.png"));
        hardFlags.add(new Flag("Mauritania", "images\\hard\\mr.png"));
        hardFlags.add(new Flag("Cape Verde", "images\\hard\\cv.png"));
    }


    public List<Flag> getFlags(String difficulty) {
        switch (difficulty) {
            case "Medium":
                return mediumFlags;
            case "Hard":
                return hardFlags;
            case "Easy":
            default:
                return easyFlags;
        }
    }
}

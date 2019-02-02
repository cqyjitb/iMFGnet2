package yj.core.wipqcparamlines.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yj.core.cardh.dto.Cardh;
import yj.core.cardh.mapper.CardhMapper;
import yj.core.marc.dto.Marc;
import yj.core.marc.mapper.MarcMapper;
import yj.core.wipcurlzk.dto.Curlzk;
import yj.core.wipcurlzk.mapper.CurlzkMapper;
import yj.core.wiplines.dto.Lines;
import yj.core.wiplines.mapper.LinesMapper;
import yj.core.wipqcparamlines.dto.QcparamLines;
import yj.core.wipqcparamlines.mapper.QcparamLinesMapper;
import yj.core.wipqcparamlines.service.IQcparamLinesService;
import org.springframework.transaction.annotation.Transactional;
import yj.core.zwipq.dto.Zwipq;
import yj.core.zwipq.mapper.ZwipqMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QcparamLinesServiceImpl extends BaseServiceImpl<QcparamLines> implements IQcparamLinesService{
    @Autowired
    private QcparamLinesMapper qcparamLinesMapper;
    @Autowired
    private LinesMapper linesMapper;
    @Autowired
    private CurlzkMapper curlzkMapper;
    @Autowired
    private CardhMapper cardhMapper;
    @Autowired
    private MarcMapper marcMapper;
    @Autowired
    private ZwipqMapper zwipqMapper;

    @Override
    public QcparamLines selectForJj(Long line_id, String werks) {
        return qcparamLinesMapper.selectForJj(line_id,werks);
    }

    @Override
    public QcparamLines selectForYz(Long line_id, String werks) {
        return qcparamLinesMapper.selectForYz(line_id,werks);
    }

    @Override
    public List<QcparamLines> selectByScale(String deptId, Long lineId) {
        List<QcparamLines> qcparamLines = new ArrayList<QcparamLines>();
        QcparamLines qcparamLine = new QcparamLines();
        Marc marc = new Marc();
        Zwipq zwipq = new Zwipq();
        String name = linesMapper.selectByUnitCode(deptId);
        List<Lines> lines = linesMapper.selectLov(deptId,lineId);
        if(lines.size() > 0){
            for(int i=0;i<lines.size();i++){
                Curlzk curlzk = curlzkMapper.selectById(lines.get(i).getLineId(),null);
                if(curlzk != null){
                    Cardh cardh = cardhMapper.selectByBarcode(curlzk.getZpgdbar());
                    if(cardh != null){
                        marc = marcMapper.selectByMatnr(cardh.getMatnr());
                        zwipq = zwipqMapper.selectByLineIdMatnr2(lines.get(i).getLineId(),cardh.getMatnr());
                        qcparamLine = qcparamLinesMapper.selectByLineId(lines.get(i).getLineId());
                        if(qcparamLine != null){
                            Double wipSqty = qcparamLine.getWipSqty();
                            qcparamLine.setDeptId(deptId);
                            qcparamLine.setName(name);
                            qcparamLine.setMatnr2(cardh.getMatnr());
                            qcparamLine.setMaktx(marc.getMaktx());
                            qcparamLine.setZsxnum(zwipq.getZsxnum());
                            if(wipSqty == null || wipSqty == 0){
                                qcparamLine.setScale("0%");
                            }else {
                                qcparamLine.setScale(Math.round(((zwipq.getZsxnum() - wipSqty)/wipSqty)*100) + "%");
                            }
                            if(zwipq.getGmein() == null || zwipq.getGmein() == ""){
                                qcparamLine.setGmein("ST");
                            }else{
                                qcparamLine.setGmein(zwipq.getGmein());
                            }
                            if(wipSqty != null || zwipq.getZsxnum() != null){
                                qcparamLines.add(qcparamLine);
                            }
                        }
                    }
                }
            }
        }
        return qcparamLines;
    }
}
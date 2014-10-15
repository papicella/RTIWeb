package com.pivotal.rtiweb.utils;

public interface Constants
{
    public static final String interfaceCount =
            "            select * from " +
                    "            (select " +
                    "                   case when dr_type = 2 then 'A' " +
                    "                        when dr_type = 3 then 'Gb' " +
                    "                        when dr_type = 5 and oracompat.instr(status, ':'::text, 1, 8) = 0 then 'IuPS' " +
                    "                        when dr_type = 5 and oracompat.instr(status, ':'::text, 1, 8) > 0 then 'IuCS' " +
                    "                        when dr_type = 9 then 'S1MME' " +
                    "                        when dr_type = 10 then 'GnGi' " +
                    "                   end interface, " +
                    "                   count(*) " +
                    "            from   iphonetrans_nrt " +
                    "            where  gploaded_time >= (now() - '00:01:00'::interval)::timestamp without time zone AND gploaded_time < now()::timestamp without time zone " +
                    "            group by 1 " +
                    "            order by 1) a";
}
